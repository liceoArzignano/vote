package it.liceoarzignano.vote.ui.components

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.geom.RoundRectangle2D

class Toast
private constructor(private val parent: JFrame) : JDialog(parent) {
    private val BACKGROUND = "#212121"
    private val FOREGROUND = "#fafafa"
    private var message: String = "???"

    private fun create() {
        layout = GridBagLayout()
        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                shape = RoundRectangle2D.Double(0.0, 0.0, width.toDouble(), height.toDouble(),
                        RADIUS.toDouble(), RADIUS.toDouble())
            }
        })

        isAlwaysOnTop = true
        isUndecorated = true
        focusableWindowState = false
        modalityType = ModalityType.MODELESS
        setSize(message.length * 12, 25)
        contentPane.foreground = Color.decode(FOREGROUND)
        contentPane.background = Color.decode(BACKGROUND)

        val label = JLabel(message)
        label.foreground = Color.decode(FOREGROUND)
        label.background = Color.decode(BACKGROUND)
        add(label)

    }

    private fun fadeIn() {
        val timer = Timer(REFRESH_RATE, null)
        timer.isRepeats = true
        timer.addActionListener(object : ActionListener {
            private var opacity = 0f
            override fun actionPerformed(e: ActionEvent) {
                opacity += ALPHA_INCREMENT
                setOpacity(Math.min(opacity, MAX_ALPHA))
                if (opacity >= MAX_ALPHA) {
                    timer.stop()
                }
            }
        })

        opacity = 0f
        timer.start()

        location = toastLocation
        isVisible = true
    }

    private fun fadeOut() {
        val timer = Timer(REFRESH_RATE, null)
        timer.isRepeats = true
        timer.addActionListener(object : ActionListener {
            private var opacity = MAX_ALPHA
            override fun actionPerformed(e: ActionEvent) {
                opacity -= ALPHA_INCREMENT
                setOpacity(Math.max(opacity, 0.0f))
                if (opacity <= 0.0f) {
                    timer.stop()
                    isVisible = false
                    dispose()
                }
            }
        })

        opacity = MAX_ALPHA
        timer.start()
    }

    private val toastLocation: Point
        get() {
            val parentLocation = parent.location
            val x = (parentLocation.x + (parent.width - this.width).toDouble() / 2.0).toInt()
            val y = parentLocation.y + (parent.height - this.height) - 36
            return Point(x, y)
        }

    @Throws(java.io.IOException::class, java.lang.ClassNotFoundException::class)
    private fun readObject(stream: java.io.ObjectInputStream) {
        stream.close()
        throw java.io.IOException("ReadObject is not supported!")
    }

    fun display() {
        Thread {
            try {
                create()
                fadeIn()
                Thread.sleep(8000L)
                fadeOut()
            } catch (e: java.lang.InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }

    companion object {
        private val MAX_ALPHA = 1f
        private val ALPHA_INCREMENT = 0.05f
        private val REFRESH_RATE = 10
        private val RADIUS = 4

        fun makeToast(parent: JFrame, message: String): Toast {
            val toast = Toast(parent)
            toast.message = message

            return toast
        }
    }

}
