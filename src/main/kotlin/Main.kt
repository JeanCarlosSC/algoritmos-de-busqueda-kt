import sRAD_kt.logic.extention.Extension.isInt
import java.io.*
import kotlin.system.exitProcess


var estructura: Estructura? = null

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
    val hayEstructura = hayEstructura()
    if(hayEstructura) {
        println("1. Ingresar claves\n" +
                "2. Ver claves\n" +
                "3. Buscar claves\n" +
                "4. Modificar claves\n" +
                "5. Eliminar claves\n" +
                "6. Modificar estructura\n" +
                "7. Eliminar estructura\n" +
                "8. Terminar ejecución")
    }
    else {
        println("1. Crear estructura\n" +
                "2. Terminar ejecución")
    }
    if(!hayEstructura()){
        val option = readIntFromUntil(1,2)
        when(option) {
            1 -> crearEstructura()
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

fun crearEstructura() {
    println("\n- - - - Creando estructura - - - -")

    print("Ingrese la cantidad de dígitos de cada clave: ")
    val nDigitos: Int = readIntFrom(1)

    print("Ingrese el tamaño/capacidad de la estructura: ")
    val size: Int = readIntFrom(1)

    estructura = Estructura(nDigitos, size)
    println("Estructura creada satisfactoriamente")
    gotoMenu()
}

fun ingresarClaves() {
    println("- - - - Ingreso de claves - - - -")
    var entrada: String?
    do {
        print("Ingrese la clave deseada o x para ir al menú principal: ")
        entrada = readlnOrNull()
        if (entrada == "x") {
            break
        }
        else if (entrada != null && isInt(entrada) && entrada.toInt()>=0
            && entrada.toInt()<=estructura!!.getMaxValue()) {
            if(!estructura!!.insertar(entrada.toInt())){
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
            println("Por favor ingrese una clave válida para la estructura de datos creada")
        }
    } while (true)
    guardar()
    gotoMenu()
}

fun verClaves() {
    println("\n- - - - Mostrando claves - - - -")
    println(estructura!!.toString())
    gotoMenu()
}

fun buscarClaves() {
    println("- - - - Búsqueda de claves - - - -")
    println("Ingrese el número del algoritmo de búsqueda que desea usar")
    println("1. Búsqueda secuencial")
    val option: Int
    do {
        val entrada = readIntFrom(0)
        if(entrada>1) {
            println("Por favor ingrese una de las opciones")
        }
        else {
            option = entrada
            break
        }
    } while (true)
    when(option) {
        1 -> busquedaSecuencial()
    }
}

fun busquedaSecuencial() {
    print("Ingrese la clave que desea buscar: ")
    val clave = readIntFromUntil(0, estructura!!.getMaxValue())
    estructura!!.buscarSecuencialmente(clave)
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
    println("Eliminando estructura")
}

fun terminarEjecucion() {
    println("Ejecución terminada exitosamente")
    exitProcess(0)
}

fun hayEstructura(): Boolean {
    return estructura != null
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
    objectOutputStream.writeObject(estructura)
    objectOutputStream.flush()
    objectOutputStream.close()
}

fun cargar() {
    val fileInputStream = FileInputStream("data.txt")
    val objectInputStream = ObjectInputStream(fileInputStream)
    estructura = objectInputStream.readObject() as Estructura
    objectInputStream.close()
}