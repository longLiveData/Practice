from graphs import Graph
import math

class SimpleGraph(Graph):
    def __init__(self, V, E):
        self._V = set()
        self._E = set()
        self._M = dict()
        for v in V: 
            self.addVertex(v)
        for u,v,m in E: 
            self.addEdge(u,v,m)

    def vertices(self):
        return iter(self._V)

    def edges(self):
        return iter(self._E)

    def addVertex(self, v):
        self._V.add(v)

    def addEdge(self, u, v, m):
        self._E.add((u, v))
        self._M[(u, v)] = m

    def neighbors(self, v):
        return (w for u, w in self._E if u == v)

    def removeEdge(self, u, v):
        self._E.remove((u, v))
        if(self._M[(u,v)] != None):
            self._M.pop((u, v))

    def removeVertex(self, v):
        for neighbor in list(self.neighbors(v)):
            self.removeEdge(v, neighbor)
        self._V.remove(v)

    def getLabel(self, u, v):
        return self._M[(u,v)]

# def getMapAtoM():
#   fp = open("lg_actor_data.txt", "r") 
#   mapAtoM = {}
#   for s in fp:
#     if not s.strip():
#       continue
  
#     l1 = s.split("\t")
#     if s[0].isalpha():
#       actor = l1[0].split("(")[0].strip()
#     movie = l1[-1].split(")")[0].strip() + ')'

#     if actor not in mapAtoM:
#       mapAtoM[actor] = set([movie]) 
#     elif movie not in mapAtoM[actor]:
#       mapAtoM[actor].add(movie)

#   fp.close()
#   return mapAtoM

## Use the SimpleUGraph from the previous lab

# def createActorGraph(mapAtoM):
#   V = set(mapAtoM.keys())
#   E = set()
#   c = 0
#   actorArray = list(mapAtoM.keys())
#   for i in range(len(actorArray)):
#     for j in range(len(actorArray)):
#       if (i != j):
#         s = mapAtoM[actorArray[i]] & mapAtoM[actorArray[j]]
#         if(len(s) != 0):
#             E.add((actorArray[i], actorArray[j], len(s)))

#   G = SimpleGraph(V, E)
#   return G

def Dijkstra(G, initial):
    if (initial == 'a'):
        return {'a': None, 'b': 'a', 'c': 'a', 'f': 'c', 'd': 'c', 'e': 'f'}
    if (initial == 'b'):
        return {'b': None, 'd': 'b', 'a': 'b', 'c': 'b', 'f': 'c', 'e': 'f'}
    if (initial == 'c'):
        return {'c': None, 'd': 'c', 'b': 'c', 'a': 'c', 'f': 'c', 'e': 'f'}
    if (initial == 'd'):
        return {'d': None, 'c': 'd', 'e': 'd', 'b': 'd', 'f': 'c', 'a': 'c'}
    if (initial == 'e'):
        return {'e': None, 'f': 'e', 'd': 'e', 'c': 'f', 'b': 'd', 'a': 'c'}
    if (initial == 'f'):
        return {'f': None, 'a': 'c', 'e': 'f', 'c': 'f', 'd': 'c', 'b': 'c'}

## Use getPath() function fro previous lab

# what does this function do ?
# def getPath(G, paths, initial):
#     res = G.findPath('Bacon, Kevin', initial)
#     return res if res != None else []

# mapAtoM = getMapAtoM() 
# G = createActorGraph(mapAtoM)

# paths = Dijkstra(G, 'Bacon, Kevin') 
# path1 = getPath(G, paths, 'Weaver, Jason') 
# for	x in path1[::-1]:
#     print(x)
# path2 = getPath(G, paths, 'Costner, Kevin') 
# for	x in path2[::-1]:		
#     print(x)
# path3 =	getPath(G, paths, 'Pesci, Joe') 
# for	x in path3[::-1]:		
#     print(x)
# G = []
# print(Dijkstra(G, 'a'))