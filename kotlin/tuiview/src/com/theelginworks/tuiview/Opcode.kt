package com.theelginworks.tuiview

enum class Opcode(
        val code:Int,
        val numArgs:Int,
        val argTypes:Array<ArgTypes>
) {
    HALT(0,0, argTypes = arrayOf()),
    CLEAR(1, 0, argTypes = arrayOf()),
    DRAW_CALIBRATION(2, 0, argTypes = arrayOf());

    companion object Factory {
        fun create(code:Int):Opcode {
            for(o in Opcode.values()) {
                if(o.code == code)
                    return o
            }

            throw InvalidOpcodeException(code)
        }
    }
}

class InvalidOpcodeException(val code:Int) : Throwable() {
    override fun toString(): String {
        return "$code is not a valid opcode"
    }
}