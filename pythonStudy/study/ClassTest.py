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


class SlotTestWithDict:
    __slots__ = ['age', 'name', 'job', "__dict__"]


class SlotTestWithConstruction:
    __slots__ = ['age', 'name', 'job']

    def __init__(self): pass

    # self.d = 1  can't define new variable


class SlotTestWithDictAndConstruction:
    __slots__ = ['age', 'name', 'job', "__dict__"]
    '''
     if you define a __dict__ in __slots__, the python won't check property
     Because it will save in __dict__
    '''

    def __init__(self):
        self.d = 1


class SlotSub(SlotTest):
    def __init__(self):
        self.a = 1


slotTest = SlotTest()
slotTest.age = 1
# slotTest.abc =  2 will raise exception
slotTestWithDict = SlotTestWithDict
print(slotTestWithDict.__dict__)

slotTestWithConstruction = SlotTestWithConstruction()

slotTestWithDictAndConstruction = SlotTestWithDictAndConstruction()
print(slotTestWithDictAndConstruction.d)
slotTestWithDictAndConstruction.c = 5


class TestStaticMethod:
    numInstances = 0  # Use static method for class data
    def imeth(self, x):  # Normal instance method: passed a self
        print([self, x])

    def smeth(x):  # Static: no instance passed
        print([x])

    def cmeth(cls, x):  # Class: gets class, not instance
        print([cls, x])

    def printNumInstances():
        print("Number of instances: %s" % TestStaticMethod.numInstances)

    printNumInstances = staticmethod(printNumInstances)      #this statemnt must exist to make printNumInstance can work

    smeth = staticmethod(smeth)  # Make smeth a static method (or @: ahead)
    cmeth = classmethod(cmeth)  # Make cmeth a class method (or @: ahead)
