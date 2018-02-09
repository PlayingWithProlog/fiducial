package com.theelginworks.tuiview

import java.awt.*
import javax.swing.JPanel
/*
Base class for all TUI panels

 */
open class TUIPanel : JPanel() {

}

class BasicTUIPanel() : TUIPanel() {
    private val behaviors: MutableList<Behavior> = ArrayList<Behavior>()

    init {
        this.background = Color.BLACK
    }

    override fun paintComponent(p0: Graphics?) {
        super.paintComponent(p0)
        if (p0 != null) {
            val g2d: Graphics2D = p0 as Graphics2D

            for(b in behaviors) {
                b.paint(g2d, this.bounds)
            }
        }
    }

    fun addBehavior(b: Behavior) {
        for(bb in behaviors){
            if(bb.merge(b))
                return
        }
        behaviors.add(b)
        this.repaint(10L)
    }
}

interface Behavior {
    fun merge(b: Behavior): Boolean {
        return false
    }

    fun paint(g: Graphics2D, bounds: Rectangle)
}

val calibration_colors: Array<Color> = arrayOf(
        Color.BLUE,
        Color.CYAN,
        Color.GREEN,
        Color.MAGENTA,
        Color.RED,
        Color.ORANGE,
        Color.YELLOW,
        Color.pink
);

class CalibrationCircleBehavior() : Behavior {
    override fun merge(b: Behavior): Boolean {
        return b is CalibrationCircleBehavior
    }

    override fun paint(g: Graphics2D, bounds: Rectangle) {
        var i: Int = 0

        for(i in 0..7) {
            g.stroke = BasicStroke(5.0F)
            g.color = calibration_colors[i]
        }
    }
}
