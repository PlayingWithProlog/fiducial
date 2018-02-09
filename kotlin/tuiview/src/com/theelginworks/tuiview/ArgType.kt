package com.theelginworks.tuiview

enum class ArgTypes() {
    STRING {
        override fun asForm(s: String): String {
            return s
        }
    },
    INT {
        override fun asForm(s: String): Int {
            try {
                return s.toInt()
            } catch(e: NumberFormatException) {
                println("$s is not an integer, need int for this argument")
                return 0
            }
        }
    };

    abstract fun asForm(s: String): Any


}