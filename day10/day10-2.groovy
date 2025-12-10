@Grab(group='tools.aqua', module='z3-turnkey', version='4.12.2.1')
import com.microsoft.z3.*

def input = new File('day10.txt').readLines()*.trim().findAll { it }

def total = 0
input.each { line ->
    def parts = line.split(' ').toList()
    parts.remove(0)
    def req = parts.removeLast()[1..-2].split(',')*.toInteger()
    def btns = parts.collect { it[1..-2].split(',')*.toInteger() }

    def ctx = new Context()
    def opt = ctx.mkOptimize()
    def vars = []

    btns.size().times { i ->
        def v = ctx.mkIntConst(((char)(i + 97)).toString())
        opt.Add(ctx.mkGe(v, ctx.mkInt(0)))
        vars << v
    }

    req.size().times { i ->
        def cond = ctx.mkInt(0)
        btns.size().times { j ->
            if (btns[j].contains(i)) cond = ctx.mkAdd(cond, vars[j])
        }
        opt.Add(ctx.mkEq(cond, ctx.mkInt(req[i])))
    }

    def sum = vars.inject(ctx.mkInt(0)) { a, v -> ctx.mkAdd(a, v) }
    opt.MkMinimize(sum)

    if (opt.Check() == Status.SATISFIABLE) {
        total += opt.Model.eval(sum, false).toString().toInteger()
    }

    ctx.close()
}

println total
