def lines = new File('day3.txt').readLines()
def maxes = []

def findLargest12DigitNumber = { line ->
    def result = ''
    def len = 12
    def curr = 0

    while (len  > 0) {
        def dig = -1
        def pos = -1
        def lim = line.length()-len+1

        for (int i = curr; i < lim; i++) {
            def digit = Integer.parseInt(line[i])
            if (digit > dig) {
                dig = digit
                pos = i
            }
            if (digit == 9) break
        }

        result += dig
        curr = pos + 1
        len--
    }
    result
}

lines.each {
    maxes << findLargest12DigitNumber(it).toBigInteger()
}

println maxes.sum()
