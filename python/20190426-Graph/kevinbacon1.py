from graphs import Graph

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
        if m != None:
            self._M[(u, v)] = m

    def neighbors(self, v):
        return (w for u, w in self._E if u == v)

    def removeEdge(self, u, v):
        self._E.remove((u, v))
        if(self._M[(u,v)] != None):
            self._M.pop((u,v))

    def removeVertex(self, v):
        for neighbor in list(self.neighbors(v)):
            self.removeEdge(v, neighbor)
        self._V.remove(v)

    def getLabel(self, u, v):
        return self._M[(u,v)]


def getMapAtoM():
  fp = open("lg_actor_data.txt", "r") 
  mapAtoM = {}
  for s in fp:
    if not s.strip():
      continue
  
    #print(s)
    l1 = s.split("\t")
    if s[0].isalpha():
      actor = l1[0].split("(")[0].strip()
    movie = l1[-1].split(")")[0].strip() + ')'
    # print ("ACTOR:",actor)
    # print("MOVIE:", movie)  

    if actor not in mapAtoM:
      mapAtoM[actor] = set([movie]) 
    elif movie not in mapAtoM[actor]:
      mapAtoM[actor].add(movie)

  fp.close()
  return mapAtoM

## Use the SimpleGraph from the previous lab

def createActorGraph(mapAtoM):
  V = set(mapAtoM.keys())
  E = set()
  c = 0
  actorArray = list(mapAtoM.keys())
  for i in range(len(actorArray)):
    for j in range(len(actorArray)):
      s = mapAtoM[actorArray[i]] & mapAtoM[actorArray[j]]
      if(len(s) != 0):
        E.add((actorArray[i], actorArray[j], len(s)))

  G = SimpleGraph(V, E)
  return G

def KBNcompute(G, act):
  if(act == 'Bacon, Kevin'):
      return 0
  kb = dict()
  newactor = ['Bacon, Kevin']
  d = 0
  kb[newactor[0]] = d
  while(True):
    d += 1
    actor = newactor
    newactor = []
    for a in actor:
      for nei in G.neighbors(a):
        if(nei == act):
          return d
        if (nei not in kb.keys()):
          kb[nei] = d
          newactor.append(nei)
    if(len(kb.keys()) >= 500):
      break

  return float('inf')

mapAtoM = getMapAtoM() 
G = createActorGraph(mapAtoM) 
k = KBNcompute(G, 'Bacon, Kevin') # should return 1 
print(k)