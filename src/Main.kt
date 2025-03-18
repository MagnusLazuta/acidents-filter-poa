import btreeplus.*
import btreeplus.BNode

fun main() {

    var tree = BTree(1)
    var option = -1

    while(option != 0){
        println("""
                Escolha uma das alternativas abaixo: 
                1 - insere uma key nova;
                2 - printa a arvore;
                3 - saia do programa.
                """)

        option = readln().toInt()
        when(option){
            1 -> tree.addOnTree(readln(), "file.h", 0)
            2 -> tree.printTree()
            3 -> option = 0
        }

    }



}