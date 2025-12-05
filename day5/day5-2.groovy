def db = new File('day5.txt').text.split(/\n\n/)
def rawRanges = db[0].split(/\n/)
def ranges = [] as List<List<Double>>
rawRanges.each {
    def parts = it.split(/\-/)
    def start = parts[0].toDouble()
    def end = parts[1].toDouble()
    ranges << [start, end]
}
ranges.sort { it[0] }
def merged = []
def current = ranges[0]

ranges[1..-1].each { range ->
    if (range[0] <= current[1] + 1) {
        current[1] = Math.max(current[1], range[1])
    } else {
        merged << current
        current = range
    }
}
merged << current
def count = merged.sum { it[1] - it[0] + 1d }
println count as BigInteger