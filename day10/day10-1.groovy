def input = new File('day10.txt').readLines()*.trim().findAll { it }

def solve(req, btns, lts, idx, depth) {
    for (int i = idx; i < btns.size(); i++) {
        def lts2 = lts.collect()
        btns[i].each { lts2[it] = !lts2[it] }
        if (lts2.withIndex().every { val, j -> val == req[j] }) return depth
    }

    def min = 0
    for (int i = idx; i < btns.size(); i++) {
        def lts2 = lts.collect()
        btns[i].each { lts2[it] = !lts2[it] }
        def res = solve(req, btns, lts2, i + 1, depth + 1)
        if (min == 0 || (res > 0 && res < min)) min = res
    }

    return min
}

def total = 0
input.each { line ->
    def parts = line.split(' ')
    def req = parts[0][1..-2].collect { it == '#' }
    def btns = parts[1..-2].collect { it[1..-2].split(',')*.toInteger() }
    def lts = [false] * req.size()
    total += solve(req, btns, lts, 0, 1)
}

println total
