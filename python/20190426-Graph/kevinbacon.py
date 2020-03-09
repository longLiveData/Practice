from graphs import SimpleUGraph

def getMapAtoM():
  fp = open("lg_actor_data.txt", "r") 
  mapAtoM = {}
  for s in fp:
    if not s.strip():
      continue
  
    l1 = s.split("\t")
    if s[0].isalpha():
      actor = l1[0].split("(")[0].strip()
    movie = l1[-1].split(")")[0].strip() + ')'

    if actor not in mapAtoM:
      mapAtoM[actor] = set([movie]) 
    elif movie not in mapAtoM[actor]:
      mapAtoM[actor].add(movie)

  fp.close()
  return mapAtoM

## Use the SimpleUGraph from the previous lab

def createActorGraph(mapAtoM):
  V = set(mapAtoM.keys())
  E = set()
  c = 0
  actorArray = list(mapAtoM.keys())
  for i in range(len(actorArray)):
    for j in range(i+1, len(actorArray)):
      s = mapAtoM[actorArray[i]] & mapAtoM[actorArray[j]]
      if(len(s) != 0):
        E.add((actorArray[i], actorArray[j]))
    
  G = SimpleUGraph(V, E)
  return G

def KBNcompute(G,	act):
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

mapAtoM	=	getMapAtoM() 
print(mapAtoM['Rock, Chris']) # shoule print movies
G	=	createActorGraph(mapAtoM) 
print	('Bacon, Kevin' in G.neighbors('Costner, Kevin'))	#	should	print	True 
k	=	KBNcompute(G, 'Costner, Kevin') #	should	return	1 
print(k)