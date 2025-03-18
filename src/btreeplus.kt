package btreeplus

class BTree(
    private val size : Int,
    private var source : BNode = BNode(size)){

    fun addOnTree(keyValue : String, nameFile : String, position : Int ){
        source.addOnTree(Key(keyValue, mutableListOf(Pair(nameFile,position))))

        while(source.father != null){
            source = source.father!!
        }
    }

    fun printTree(){
        source.printTree()
    }
}

class BNode(
    private val size : Int,
    private var sheet : Boolean = true,
    private var keys : MutableList<Key> = mutableListOf(),
    private var children : MutableList<BNode> = mutableListOf(),
    internal var father :  BNode? = null
){

    private fun addKeyOnNode(newKey : Key){
        keys.add(newKey)
        keys.sortBy { it.keyValue }
        children.sortBy { it.keys[0].keyValue }
    }

    private fun addChildOnNode(child : BNode){
        children.add(child)
        keys.sortBy{ it.keyValue }
        children.sortBy { it.keys[0].keyValue }
    }

    //particiona somente quando o numero de chaves > size * 2
    private fun partition(){
        var new_node = BNode(size)
        var middle = this.keys[(size/2)+1]
        this.keys.remove(middle)

        //passar todas as chaves size/2 maiores para o novo nodo
        for(i in size/2+1..this.keys.size -1){
            var temp = keys[i]
            new_node.addKeyOnNode(temp)
            keys.remove(temp)
        }

        //se não for uma folha, transfere os filhos
        if(!sheet){
            for(i in children.size/2..children.size - 1){
                var temp = children[i]
                new_node.addChildOnNode(temp)
                temp.father = new_node
            }
            children -= new_node.children
            new_node.sheet = false
        }

        //Se o nodo não tiver um pai, crie um pai
        if(this.father == null){
            var father = BNode(size)
            this.father = father
            new_node.father = father
            father.sheet = false
            father.addChildOnNode(this)
            father.addChildOnNode(new_node)
        }

        //senão, adicione o filho a lista de filhos do pai e vincule o pai ao filho
        else{
            new_node.father = this.father
            father!!.addChildOnNode(new_node)
        }

        father!!.addKeyOnNode(middle)
        return
    }

    fun addOnTree(key : Key){

    //Procure se a key está nodo
        for(i in keys){
            if(i.keyValue == key.keyValue){
                i.addRegistration(key.values)
                return
            }
        }

    //verificar se é uma folha
        if(sheet){
            //adicione uma nova key
            addKeyOnNode(key)

            //Se o número de chaves > size * 2
            if(keys.size > size*2){
                //faça o particionamento
                partition()
            }
            //Se não, apenas retorne
            return
        }

        else{
            var index = keys.size - 1
            //var string_children = children[index].keys[0].keyValue

            while(index >= 0  &&  keys[index].keyValue.compareTo(key.keyValue) > 0){
                index--
                //string_children = children[index].keys[0].keyValue
            }

            children[index+1].addOnTree(key)
        }

        if(keys.size > size * 2){
            partition()
        }
        return
    }

    fun printTree(level : Int = 0){
        print("\nlevel $level: ")
        for(i in keys){
            print("${i.keyValue} ")
        }

        if(!sheet){
            for(child in children){
                child.printTree(level+1)
            }
        }

        else{
            return
        }

    }
}

data class Key(val keyValue : String, var values : MutableList<Pair<String, Int>> = mutableListOf()){
    fun addRegistration(newValues : MutableList<Pair<String, Int>>){
        values += newValues
    }
}