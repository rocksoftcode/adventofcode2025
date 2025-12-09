def input = new File('day9.txt').readLines()*.trim().collect { Point.parse(it) }
def rects = (0..<input.size() - 1)
        .collectMany { i -> ((i + 1)..<input.size())
                .collect { j -> Rect.fromPoints(input[i], input[j]) } }
println rects*.area().max()

def lines = (0..<input.size()).collect { i -> Rect.fromPoints(input[i], input[(i + 1) % input.size()]) }
println rects.findAll { r -> lines.every { !r.overlaps(it) } }*.area().max()

class Point {
    long x, y

    Point(long x, long y) {
        this.x = x
        this.y = y
    }

    static Point parse(String pair) {
        def coord = pair.split(',')
        new Point(coord[0] as long, coord[1] as long)
    }
}

class Rect {
    Point min, max

    Rect(Point min, Point max) {
        this.min = min
        this.max = max
    }

    static Rect fromPoints(Point a, Point b) {
        new Rect(new Point(Math.min(a.x, b.x), Math.min(a.y, b.y)), new Point(Math.max(a.x, b.x), Math.max(a.y, b.y))
        )
    }

    boolean overlaps(Rect that) {
        min.x < that.max.x && max.x > that.min.x && min.y < that.max.y && max.y > that.min.y
    }

    long area() {
        (max.x - min.x + 1) * (max.y - min.y + 1)
    }
}

