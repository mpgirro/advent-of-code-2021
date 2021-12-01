class Util {

    companion object {

        fun readFileFromClasspath(fileName: String): String = this::class.java.getResource(fileName).readText(Charsets.UTF_8)

    }

}