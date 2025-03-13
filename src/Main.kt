import btreeplus.*
import btreeplus.BNode

fun main() {
    var tree = BNode(size = 1)
    var child1 = BNode(size = 1)
    var child2 = BNode(size = 1)

    tree.keys.add(Key("3", mutableListOf(Pair("C:/21",0))))
    child1.keys.add(Key("1", mutableListOf(Pair("C:/22",0))))
    child1.keys.add(Key("2", mutableListOf(Pair("C:/21",0))))
    child2.keys.add(Key("4", mutableListOf(Pair("C:/21",0))))
    child2.keys.add(Key("5", mutableListOf(Pair("C:/21",0))))

    tree.children.add(child2)
    tree.children.add(child1)

    print(tree.children)

    tree.children.sortBy { it.keys[0].key }

    print(tree.children)




//    println(tree.keys)
//
//    tree.keys.sortBy { it.key }
//
//    println(tree.keys)



}