def input = new File('day11.txt').readLines()
def connections = [:]
input.each {
    def parts = it.split(/:\s/)
    def node = parts[0]
    def links = parts[1].split(/\s/).toList()
    connections[node] = links
}

def m = [:]
traverse = { n, t ->
    if (n == 'dac') t |= 1
    if (n == 'fft') t |= 2
    if (n == 'out') return t == 3 ? 1 : 0
    def key = n + '|' + t
    if (m[key] != null) return m[key]
    def count = 0L
    for (nxt in connections[n] ?: []) {
        count += traverse(nxt, t)
    }
    m[key] = count
    return count
}
println traverse('svr', 0)