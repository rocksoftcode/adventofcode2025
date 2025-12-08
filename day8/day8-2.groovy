def coords = new File('day8.txt').readLines().collect { it.split(',').collect { it as Integer } }.withIndex().collect { val, idx -> [idx, val] }
def circuits = (0..<coords.size()).collect { [it] as Set }
def calcDist = { c1, c2 ->
    def (x1, y1, z1) = c1
    def (x2, y2, z2) = c2
    Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) + Math.pow((z1 - z2), 2)
}

def distances = [:]
coords.eachWithIndex { item1, xi ->
    def (idx1, xv) = item1
    def rest = coords.drop(xi + 1)

    rest.each { item2 ->
        def (idx2, rv) = item2
        def dist = calcDist(xv, rv)
        if (!distances.containsKey(dist)) {
            distances[dist] = [] as Set
        }
        distances[dist] << idx1
        distances[dist] << idx2
    }
}

def sorted = distances.sort { it.key }.collect { it.value }
sorted.take(1000).each { c ->
    def cs = c.collect { x -> circuits.findIndexOf { y -> y.contains(x) } }
    if (cs[0] == cs[1]) return
    def cMin = cs[0] < cs[1] ? cs[0] : cs[1]
    def cMax = cMin == cs[0] ? cs[1] : cs[0]
    circuits[cMin] = circuits[cMin] + circuits[cMax]
    circuits.remove(cMax)
}

def same = circuits.findAll { it.size() == 1 }
        .collect { sorted.findIndexOf { y -> y.contains(it[0]) } }
        .sort { -it }[0]

println sorted[same].collect { y -> coords[y][1][0] }.inject(1L) { a, v -> a * v }

