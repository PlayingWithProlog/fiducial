package com.theelginworks.tuiview

private val numArgs : HashMap<Int, Int> = hashMapOf(0 to 0, 1 to 3)

class TUIListener(private val theView: TUIView) {
    fun listen() {

        while(true) {
            var line:String? = readLine()

            if(line != null) {
                var args = line.split(",")

                if(!parse(0, args))
                    break;
            } else {
                break;
            }
        }
        println("done listening")
        theView.close()
    }

    private tailrec fun parse(pos:Int, args: List<String>): Boolean {
        if(args.size > pos) {
            try {
                val operand = Opcode.Factory.create(args[pos].toInt())

                if(operand == Opcode.HALT)
                    return false
                else {
                    val thisArgs: List<String> = args.subList(pos, pos + operand.numArgs)
                    theView.message(operand, thisArgs)

                    return parse(pos + 1 + operand.numArgs, args)
                }
            } catch (e: NumberFormatException) {
                println("${args[pos]} is opcode? cannot be converted to a number")
                return false
            }
        } else
            return true
    }
}