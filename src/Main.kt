import btreeplus.*
import btreeplus.BNode

fun main() {

    var tree = BTree(1)
    var option = -1

    while(option != 0){
        println("""
                Escolha uma das alternativas abaixo: 
                1 - insere uma key nova;
                2 - buscar uma key na árvore
                3 - printa a arvore;
                4 - saia do programa.
                """)

        option = readln().toInt()
        when(option){
            1 -> tree.addOnTree(readln(), "file.h", 0)
            2 -> {
                println("Digite a chave que quer encontrar: ")
                var key = tree.findOnTree(readln())
                if (key != null) {
                    println("""
                                1. keyValue: ${key.keyValue}
                                2. Ocorrencias
                            """.trimIndent())
                    for(i in key.values){
                        println("arquivo: ${i.first} posicao: ${i.second}")
                    }
                }
                else{
                    println("null")
                }
            }
            3 -> tree.printTree()
            4 -> option = 0
        }

    }



}