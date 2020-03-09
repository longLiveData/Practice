class Graph:
    def addVertex(self, vert):
        #Add a vertex with key k to the vertex set.
        raise NotImplemented

    def addEdge(self, fromVert, toVert):
        #Add a directed edge from u to v.
        raise NotImplemented

    def neighbors(self):
        #Return an iterable collection of the keys of all
        #vertices adjacent to the vertex with key v.
        raise NotImplemented

    def removeEdge(self, u, v):
        #Remove the edge from vertex u to v from graph.
        raise NotImplemented

    def removeVertex(self, v):
        #Remove the vertex v from the graph as well as any edges
        #incident to v.
        raise NotImplemented

    ## Part 2
    def dfs(self, v):
        pass

    def findPath(self, u, v):
        pass

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



## Part 1
class SimpleUGraph:
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
        self._E.add((v, u))
        self._M[(v, u)] = m

    def neighbors(self, v):
        return (w for u, w in self._E if u == v)

    def removeEdge(self, u, v):
        self._E.remove((u, v))
        self._E.remove((v, u))

    def removeVertex(self, v):
        for neighbor in list(self.neighbors(v)):
            self.removeEdge(v, neighbor)
        self._V.remove(v)

## Part 3
class AdjacencyListGraph:
    def __init__(self, V, E):
        self._G = dict()
        for v in V:
            self._G[v] = []
        for u, v in E:
            self.addEdge(u, v)

    def vertices(self):
        return self._G.keys()

    def edges(self):
        E = set()
        for u in self.vertices():
            for v in self._G[u]:
                E.add((u,v))
        return iter(E)

    def addVertex(self, v):
        self._G[v] = []

    def addEdge(self, u, v):
        self._G[u].append(v)

    def neighbors(self, v):
        return iter(self._G[v])

    def removeEdge(self, u, v):
        self._G[u].remove(v)

    def removeVertex(self, v):
        for u in self.vertices():
            if v in self._G[u]:
                self.removeEdge(u,v)
        self._G.pop(v)

    def dfs(self, v1):
        vertex = list(self.vertices())
        edge = list(self.edges())
        ifProcess = [0 for i in range(len(vertex))]
        res = dict()
        res[v1] = None

        def dfsNext(vi):
            ifProcess[vertex.index(v1)] = 1
            for u,v in edge:
                if (u == vi and ifProcess[vertex.index(v)] == 0):
                    res[v] = vi
                    dfsNext(v)

        dfsNext(v1)
        return res

    def findPath(self, u, v):
        dfs = self.dfs(u)
        temp = v
        res = [temp]
        while(True):
            if(temp not in dfs.keys()):
                return None
            temp = dfs[temp]
            res.append(temp)
            if(temp == u):
                return res[::-1]


class AdjacencyListUGraph:
    def __init__(self, V, E):
        self._G = dict()
        for v in V:
            self._G[v] = []
        for u, v in E:
            self.addEdge(u, v)

    def vertices(self):
        return self._G.keys()

    def edges(self):
        E = set()
        for u in self.vertices():
            for v in self._G[u]:
                E.add((u,v))
        return iter(E)

    def addVertex(self, v):
        self._G[v] = []

    def addEdge(self, u, v):
        self._G[u].append(v)
        self._G[v].append(u) 

    def neighbors(self, v):
        return iter(self._G[v])

    def removeEdge(self, u, v):
        self._G[u].remove(v)
        self._G[v].remove(u)

    def removeVertex(self, v):
        for u in self.vertices():
            if v in self._G[u]:
                self.removeEdge(v, u)
        self._G.pop(v)

    def dfs(self, v1):
        vertex = list(self.vertices())
        edge = list(self.edges())
        ifProcess = [0 for i in range(len(vertex))]
        res = dict()
        res[v1] = None
        
        def dfsNext(vi):
            ifProcess[vertex.index(v1)] = 1
            for u,v in edge:
                if (v not in res.keys() and u == vi and ifProcess[vertex.index(v)] == 0):
                    res[v] = vi
                    dfsNext(v)

        dfsNext(v1)
        return res

    def findPath(self, u, v):
        dfs = self.dfs(u)
        temp = v
        res = [temp]
        while(True):
            if(temp not in dfs.keys()):
                return None
            temp = dfs[temp]
            res.append(temp)
            if(temp == u):
                return res[::-1]


## Part 4
class AdjacencyMatrixGraph:
    def __init__(self, V, E):
        self._L = list(V)
        self._G = [[0 for i in range(len(V))] for i in range(len(V))]
        for u,v in E:
            self.addEdge(u,v)

    def vertices(self):
        return iter(self._L)

    def edges(self):
        E = set()
        for i in range(len(self._G)):
            for j in range(len(self._G[0])):
                if self._G[i][j] == 1:
                    E.add((self._L[i],self._L[j]))
        return iter(E)

    def addVertex(self, v):
        self._L.append(v)
        self._G.append([0 for i in range(len(self._L) - 1)])
        for i in range(len(self._L)):
            self._G[i].append(0)

    def addEdge(self, u, v):
        u = self._L.index(u)
        v = self._L.index(v)
        self._G[u][v] = 1

    def neighbors(self, v):
        neighbors = set()
        v = self._L.index(v)
        for u in range(len(self._L)):
            if(self._G[v][u] == 1):
                neighbors.add(u)
        return iter(neighbors)

    def removeEdge(self, u, v):
        u = self._L.index(u)
        v = self._L.index(v)
        self._G[u][v] = 0

    def removeVertex(self, v):
        vn = self._L.index(v)
        for i in range(len(self._G)):
            self._G[i].remove(self._G[i][vn])
        self._G.remove(self._G[vn])
        self._L.remove(v)

    def dfs(self, v1):
        vertex = list(self.vertices())
        edge = list(self.edges())
        ifProcess = [0 for i in range(len(vertex))]
        res = dict()
        res[v1] = None

        def dfsNext(vi):
            ifProcess[vertex.index(v1)] = 1
            for u,v in edge:
                if (u == vi and ifProcess[vertex.index(v)] == 0):
                    res[v] = vi
                    dfsNext(v)

        dfsNext(v1)
        return res
    
    def findPath(self, u, v):
        dfs = self.dfs(u)
        temp = v
        res = [temp]
        while(True):
            if(temp not in dfs.keys()):
                return None
            temp = dfs[temp]
            res.append(temp)
            if(temp == u):
                return res[::-1]


class AdjacencyMatrixUGraph:
    def __init__(self, V, E):
        self._L = list(V)
        self._G = [[0 for i in range(len(V))] for i in range(len(V))]
        for u,v in E:
            self.addEdge(u,v)

    def vertices(self):
        return iter(self._L)

    def edges(self):
        E = set()
        for i in range(len(self._G)):
            for j in range(len(self._G[0])):
                if self._G[i][j] == 1:
                    E.add((self._L[i],self._L[j]))
        return iter(E)

    def addVertex(self, v):
        self._L.append(v)
        self._G.append([0 for i in range(len(self._L) - 1)])
        for i in range(len(self._L)):
            self._G[i].append(0)

    def addEdge(self, u, v):
        u = self._L.index(u)
        v = self._L.index(v)
        self._G[u][v] = 1
        self._G[v][u] = 1

    def neighbors(self, v):
        neighbors = set()
        v = self._L.index(v)
        for u in range(len(self._L)):
            if(self._G[v][u] == 1):
                neighbors.add(u)
        return iter(neighbors)

    def removeEdge(self, u, v):
        u = self._L.index(u)
        v = self._L.index(v)
        self._G[u][v] = 0
        self._G[v][u] = 0 

    def removeVertex(self, v):
        vn = self._L.index(v)
        for i in range(len(self._G)):
            self._G[i].remove(self._G[i][vn])
        self._G.remove(self._G[vn])
        self._L.remove(v)

    def dfs(self, v1):
        vertex = list(self.vertices())
        edge = list(self.edges())
        ifProcess = [0 for i in range(len(vertex))]
        res = dict()
        res[v1] = None
        
        def dfsNext(vi):
            ifProcess[vertex.index(v1)] = 1
            for u,v in edge:
                if (v not in res.keys() and u == vi and ifProcess[vertex.index(v)] == 0):
                    res[v] = vi
                    dfsNext(v)

        dfsNext(v1)
        return res

    def findPath(self, u, v):
        dfs = self.dfs(u)
        temp = v
        res = [temp]
        while(True):
            if(temp not in dfs.keys()):
                return None
            temp = dfs[temp]
            res.append(temp)
            if(temp == u):
                return res[::-1]



