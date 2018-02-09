package com.theelginworks.tuiview

import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JFrame.DISPOSE_ON_CLOSE

public class TUIView {
    val frame: JFrame = JFrame("HelloWorld")
    val tui = BasicTUIPanel()

    init {
        frame.defaultCloseOperation = DISPOSE_ON_CLOSE

    //    val label: JLabel = JLabel("Howdy World")
    //    frame.contentPane.add(label)
        frame.contentPane.add(tui)
        frame.minimumSize = Dimension(500, 500)

        frame.pack()
        frame.isVisible = true
    }

    fun message(operand: Opcode, args: List<String>) {
        println("Operand $operand args $args")
    }

    fun close() {
        this.frame.dispose()

        System.exit(0)
    }
}