package com.zeticai.zeticmlangellmsample

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeticai.mlange.core.model.llm.LLMQuantType
import com.zeticai.mlange.core.model.llm.LLMTarget
import com.zeticai.mlange.core.model.llm.ZeticMLangeLLMModel
import com.zeticai.zeticmlangellmsample.databinding.ActivityMainBinding
import com.zeticai.zeticmlangellmsample.domain.Message
import com.zeticai.zeticmlangellmsample.view.ChatAdapter
import com.zeticai.zeticmlangellmsample.utils.Constants
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val adapter: ChatAdapter by lazy {
        ChatAdapter()
    }

    private val model: ZeticMLangeLLMModel by lazy {
        ZeticMLangeLLMModel(
            this,
            Constants.MLANGE_PERSONAL_ACCESS_TOKEN,
            Constants.MODEL_NAME
        ) {
            runOnUiThread {
                binding.messageInput.hint = "File Downloading... ${it * 100f}%"
            }
        }
    }
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initModel()
        initBinding()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val navigationBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, navigationBars.bottom)
            insets
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    private fun initModel() {
        binding.sendButton.setEnabled(false, R.drawable.ic_send)
        binding.messageInput.hint = "model loading.."
        binding.messageInput.isEnabled = false

        Thread {
            model
            runOnUiThread {
                binding.sendButton.setEnabled(true, R.drawable.ic_send)
                binding.messageInput.hint = "Type a message"
                binding.messageInput.isEnabled = true
            }
        }.start()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.chatRecyclerView.adapter = adapter
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }

        binding.sendButton.setOnClickListener {
            binding.sendButton.setEnabled(false, R.drawable.ic_send)
            val message = binding.messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                adapter.addMessage(Message(StringBuilder(message), isFromUser = true))
                binding.messageInput.text.clear()
                binding.chatRecyclerView.smoothScrollToPosition(adapter.itemCount - 1)

                adapter.addMessage(Message(StringBuilder(), isFromUser = false))
                simulateStreamingResponse(message.trim())
            } else {
                binding.sendButton.setEnabled(true, R.drawable.ic_send)
            }
        }

    }

    private var generationLatch = CountDownLatch(0)
    private var stopGenerateToken = true

    override fun onDestroy() {
        super.onDestroy()
        if (!stopGenerateToken) {
            stopGenerateToken = true
            generationLatch = CountDownLatch(1)
            generationLatch.await(2, TimeUnit.SECONDS)
        }
        model.deinit()
    }

    private fun simulateStreamingResponse(text: String) {
        Thread {
            stopGenerateToken = false
            model.run(text)
            while (true) {
                val token = model.waitForNextToken()
                if (token == "") break

                if (stopGenerateToken) {
                    generationLatch.countDown()
                    return@Thread
                }

                runOnUiThread {
                    adapter.appendToLastMessage(token)
                }
            }
            runOnUiThread {
                binding.sendButton.setEnabled(true, R.drawable.ic_send)
            }
            stopGenerateToken = true
        }.start()
    }

    private fun ImageButton.setEnabled(enabled: Boolean, iconResId: Int) {
        isEnabled = enabled
        val originalIcon = ResourcesCompat.getDrawable(resources, iconResId, theme) ?: return
        val icon = if (enabled) originalIcon else convertDrawableToGrayScale(originalIcon)
        setImageDrawable(icon)
    }

    private fun convertDrawableToGrayScale(drawable: Drawable): Drawable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val res = drawable.mutate()
            res.colorFilter = BlendModeColorFilter(Color.GRAY, BlendMode.SRC_IN)
            return res
        }
        return drawable
    }
}