def lines = new File('day2.txt').readLines()
def invalids = []
lines.each {
    def ids = it.split(',')
    ids.each {
        def range = it.split('-')*.toBigInteger()
        (range[0]..range[1]).each {
            def v = it.toString()
            def rex = /^(\d+)\1+$/
            if (v.matches(rex)) invalids << new BigDecimal(v)
        }
    }
}
println invalids.sum()