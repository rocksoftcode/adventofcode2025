def lines = new File('day2.txt').readLines()
def invalids = []
lines.each {
    def ids = it.split(',')
    ids.each {
        def range = it.split('-')*.toBigInteger()
        (range[0]..range[1]).each {
            def v = it.toString()
            def len = v.length()
            if (len % 2 == 0) {
                def h1 = v.substring(0, (len / 2) as int)
                def h2 = v.substring((len / 2) as int, len)
                if (h1 == h2) {
                    invalids << new BigInteger(v)
                }
            }
        }
    }
}
println invalids.sum()