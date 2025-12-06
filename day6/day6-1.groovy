def problems = new File('day6.txt').readLines()
def numbers = problems[0..-2]*.trim()*.split(/\s+/).collect { it.collect { Double.valueOf(it) } }
def operations = problems.last().split(/\s+/)
def total = 0d
for (i in 0..<numbers[0].size()) {
    def op = operations[i]
    def row = numbers.collect { it[i] }
    def run = evaluate(row.join(op))
    total += run
}
println total as BigInteger