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
