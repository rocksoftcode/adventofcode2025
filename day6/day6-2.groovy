def problems = new File('day6.txt').readLines()
def operations = problems.last().split(/\s+/)
def rawNumbers = problems[0..-2]
def stage = []
def opIdx = operations.length - 1
def total = 0d

for (def x = rawNumbers[0].length() - 1; x >= 0; x--) {
    def num = ''
    for (y in 0..<rawNumbers.size()) {
        num += rawNumbers[y][x]
    }
    if (!num.trim()) {
        def eq = stage.join(operations[opIdx--])
        def res = evaluate(eq)
        total += res
        stage = []
    } else {
        stage << Double.valueOf(num.trim())
    }
}
total += evaluate(stage.join(operations[opIdx--]))
println total as BigInteger