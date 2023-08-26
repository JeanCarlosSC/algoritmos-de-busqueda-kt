import lib.sRAD_kt.logic.extention.Extension.isInt
import java.io.*
import kotlin.system.exitProcess


var arreglo: Arreglo? = null

// Spanish symbols á é í ó ú ñ
fun main() {
    println("\nBienvenido al sistema de almacenamiento SJ")
    val f = File("data.txt")
    if (f.exists() && !f.isDirectory()) {
        cargar()
    }
    gotoMenu()
}

fun gotoMenu() {
    println("\n- - - - Menú principal - - - -")
    println("Por favor ingrese el número de la acción que desea ejecutar")
    val hayArreglo = hayArreglo()
    if(hayArreglo) {
        println("1. Ingresar claves\n" +
                "2. Ver claves\n" +
                "3. Buscar claves\n" +
                "4. Modificar claves\n" +
                "5. Eliminar claves\n" +
                "6. Modificar arreglo\n" +
                "7. Eliminar arreglo\n" +
                "8. Terminar ejecución")
    }
    else {
        println("1. Crear arreglo\n" +
                "2. Terminar ejecución")
    }
    if(!hayArreglo()){
        val option = readIntFromUntil(1,2)
        when(option) {
            1 -> crearArreglo()
            else -> terminarEjecucion()
        }
    }
    else {
        val option = readIntFromUntil(1, 8)
        when(option) {
            1 -> ingresarClaves()
            2 -> verClaves()
            3 -> buscarClaves()
            4 -> modificarClaves()
            5 -> eliminarClaves()
            6 -> modificarEstructura()
            7 -> eliminarEstructura()
            else -> terminarEjecucion()
        }
    }
}

fun crearArreglo() {
    println("\n- - - - Creando arreglo - - - -")

    print("Ingrese la cantidad de dígitos de cada clave: ")
    val nDigits: Int = readIntFrom(1)

    print("Ingrese el tamaño o la capacidad máxima del arreglo: ")
    val size: Int = readIntFrom(1)

    arreglo = Arreglo(nDigits, size)
    println("Estructura creada satisfactoriamente")
    gotoMenu()
}

fun ingresarClaves() {
    println("- - - - Ingresando claves - - - -")
    var input: String?
    do {
        print("Ingrese la clave que desea insertar en el arreglo o x para ir al menú principal: ")
        input = readlnOrNull()
        if (input == "x") {
            break
        }
        else if (input != null && isInt(input) && input.toInt()>=0
            && input.toInt()<=arreglo!!.getMaxValue()) {
            if(!arreglo!!.insertar(input.toInt())){
                println("¿Desea eliminar claves?")
                println("1. Sí\n" +
                        "2. No")
                val option = readIntFromUntil(1,2)
                if(option == 1) {
                    eliminarClaves()
                }
            }
        }
        else {
            println("Por favor ingrese una clave válida para el arreglo definido")
        }
    } while (true)
    guardar()
    gotoMenu()
}

fun verClaves() {
    println("\n- - - - Mostrando claves - - - -")
    println(arreglo!!.toString())
    gotoMenu()
}

fun buscarClaves() {
    println("- - - - Buscando claves - - - -")
    println("Ingrese el número que corresponda al algoritmo de búsqueda que desea usar")
    println("1. Búsqueda secuencial")
    val option: Int
    do {
        val input = readIntFrom(0)
        if(input>1) {
            println("Por favor ingrese una de las opciones")
        }
        else {
            option = input
            break
        }
    } while (true)
    when(option) {
        1 -> busquedaSecuencial()
    }
}

fun busquedaSecuencial() {
    print("Ingrese la clave que desea buscar: ")
    val clave = readIntFromUntil(0, arreglo!!.getMaxValue())
    arreglo!!.buscarSecuencialmente(clave)
    println("\n¿Desea buscar otra clave con el algoritmo secuencial?")
    println("1. Sí\n" +
            "2. No")
    val option = readIntFromUntil(1, 2)
    if (option == 1){
        busquedaSecuencial()
    }
    else {
        gotoMenu()
    }

}
fun modificarClaves() {
    println("Modificando claves")
}

fun eliminarClaves() {
    println("Eliminando claves")
}

fun modificarEstructura() {
    println("Modificando estructura")
}

fun eliminarEstructura() {
    println("- - - - Eliminando arreglo - - - -")
    arreglo = null

    val data = File("data.txt")
    if (data.delete()) {
        println("El arreglo se ha borrado exitosamente")
    }
    else {
        println("No se ha podido borrar el arreglo")
    }
    gotoMenu()
}

fun terminarEjecucion() {
    println("Ejecucion terminada exitosamente")
    exitProcess(0)
}

fun hayArreglo(): Boolean {
    return arreglo != null
}

fun readIntFrom(a: Int): Int {
    val num: Int
    do {
        val input = readlnOrNull()
        if(input == null || !isInt(input) || input.toInt()<a) {
            println("Por favor ingrese un valor entero mayor o igual a: $a")
        }
        else {
            num = input.toInt()
            break
        }
    } while (true)
    return num
}

fun readIntFromUntil(a: Int, b: Int): Int {
    val num: Int
    do {
        val input = readlnOrNull()
        if(input == null || !isInt(input) || input.toInt()<a || input.toInt()>b) {
            print("Por favor ingrese un valor entero mayor o igual a $a y menor o igual a $b: ")
        }
        else {
            num = input.toInt()
            break
        }
    } while (true)
    return num
}

fun guardar() {
    val fileOutputStream = FileOutputStream("data.txt")
    val objectOutputStream = ObjectOutputStream(fileOutputStream)
    objectOutputStream.writeObject(arreglo)
    objectOutputStream.flush()
    objectOutputStream.close()
}

fun cargar() {
    val fileInputStream = FileInputStream("data.txt")
    val objectInputStream = ObjectInputStream(fileInputStream)
    arreglo = objectInputStream.readObject() as Arreglo
    objectInputStream.close()
}