def db = new File('day5.txt').text.split(/\n\n/)
def rawRanges = db[0].split(/\n/)
def ranges = [] as List<List<BigInteger>>
rawRanges.each {
    def parts = it.split(/\-/)
    def start = parts[0].toBigInteger()
    def end = parts[1].toBigInteger()
    ranges << [start, end]
}
def inventory = db[1].split(/\n/)*.toBigInteger()
def spoiled = inventory.count {item -> ranges.find {item >= it[0] && item <= it[1] } }
println spoiled