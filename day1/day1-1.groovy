def turns = new File('day1.txt').readLines()
def cur = 50
def password = 0
turns.each {
    def dir = it.substring(0, 1)
    def amt = it.substring(1).toInteger()
    def op = dir == 'L' ? -1 : 1
    amt.times {
        cur += op
        if (cur < 0) cur = 99
        else if (cur > 99) cur = 0
    }
    if (cur == 0) password++
}

println password