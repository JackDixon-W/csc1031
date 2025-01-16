import math

class Dot3D:
    def __init__(self, x, y, z, label=None):
        self.x = x
        self.y = y
        self.z = z
        if(label == None):
            self.label = None
        else:
            self.label = label
    
    def distance_to(self, other):
        # Calc and Return distance
        # ((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
        x = pow(other.x - self.x, 2)
        y = pow(other.y - self.y, 2)
        z = pow(other.z - self.z, 2)
        dist = math.sqrt(x + y + z)
        return dist
    
    def add_vector(self, other):
        # Add vectors and return new vector
        x = other.x + self.x
        y = other.y + self.y
        z = other.z + self.z
        label = self.label + "+" + other.label
        return Dot3D(x, y, z, label)

class Triangle3D:
    def __init__(self, edge1, edge2, edge3):
        self.edge1 = edge1
        self.edge2 = edge2
        self.edge3 = edge3

    def calculate_perimeter(self):
        # Calculates length of each side and returns perimeter
        s1 = self.edge1.distance_to(self.edge2)
        s2 = self.edge2.distance_to(self.edge3)
        s3 = self.edge3.distance_to(self.edge1)
        return s1 + s2 + s3

    def calculate_area(self):
        # A = sqrt((s * (s - edge1) * (s - edge2) * (s - edge3)))
        s = self.calculate_perimeter() / 2
        part1 = s - self.edge1.distance_to(self.edge2)
        part2 = s - self.edge2.distance_to(self.edge3)
        part3 = s - self.edge3.distance_to(self.edge1)
        return math.sqrt(s * part1 * part2 * part3)
