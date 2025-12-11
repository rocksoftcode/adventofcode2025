def input = new File('day11.txt').readLines()
def connections = [:]
input.each {
    def parts = it.split(/:\s/)
    def node = parts[0]
    def links = parts[1].split(/\s/).toList()
    connections[node] = links
}

def count = 0
traverse = { n ->
    def branches = connections[n]
    if (branches.contains('out')) count++
    else if (branches) branches.each { traverse(it) }
}

traverse('you')
println count