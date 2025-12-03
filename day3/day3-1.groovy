def lines = new File('day3.txt').readLines()
def maxes = []

lines.each {
    def jolts = it.split('') as List<String>
    def max = -1
    for (i in 0..<jolts.size() - 1) {
        for (j in i+1..<jolts.size()) {
            max = Math.max(max, "${jolts[i]}${jolts[j]}".toInteger())
        }
    }
    maxes << max
}

println maxes.sum()
