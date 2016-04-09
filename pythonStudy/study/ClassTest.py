# a = FirstClass() this can't work
class FirstClass:
    def __init__(self):
        pass

    def test_self(self):
        self.a = 5

    def print_a(self):
        print(self.a)


a = FirstClass()
a.test_self()
a.print_a()
b = FirstClass()
b.a = 6
b.print_a();


class C1:
    def __init__(self): self.__x = 1


class C2:
    def __init__(self): self.__b = 2


class C3(C1, C2): pass


I = C3()
print(I.__dict__)  # __ can use for hide property. and only C1's constructor has been execute


class SlotTest:
    __slots__ = ['age', 'name', 'job']

slotTest = SlotTest()
slotTest.age = 1
# slotTest.abc =  2 will raise exception
print(slotTest.__dict__)