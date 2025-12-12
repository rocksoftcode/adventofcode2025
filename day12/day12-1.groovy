def input = new File('day12.txt').text.split(/\n\n/)
def items = []
for (d in input[0..-2]) {
    def s = d.split(/\n/)[1..-1].join('\n')
    items << [s: s, area: s.count('#')]
}

def count = 0
for (line in input[-1].split(/\n/)) {
    def g = (line =~ /\d+/).collect { it as Integer }
    def a = g[0] * g[1]
    def req = g[2..-1].withIndex().sum { val, i -> val * items[i].area }
    if (a >= req && req / a <= 0.85) count++
}

println count