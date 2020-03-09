import sys
import math

def main():
    for a_line in sys.stdin:
        list1 = a_line.strip("\n").split(",")[1:]
        list1 = [float(i) for i in list1]
        path = get_path(list1)
        print(path)

def get_path(list1):
    # if input format is not correct
    if len(list1) % 2 == 1:
        return -1

    list2 = []
    for i in range(0, len(list1), 2):
        x = list1[i]
        y = list1[i+1]
        list2.append([x, y])

    num = len(list2)
    distance_list = [[math.inf for i in range(num)] for j in range(num)]
    for i in range(0, num):
        for j in range(i+1, num):
            d = distance(list2[i], list2[j])
            if float(d) <= 100:
                distance_list[i][j] = d

    return dijkstra(distance_list)

def distance(p1, p2):
    x1, y1 = p1
    x2, y2 = p2
    res = math.sqrt((x1-x2) ** 2 + (y1-y2) ** 2)
    return '%.2f' % res

def dijkstra(distance_list):
    if (distance_list[0] == [math.inf for i in range(len(distance_list))]):
        return -1
        
    start = 0
    end = len(distance_list) - 1
    dist = distance_list[start]
    colour = ["white" for i in range(len(distance_list[start]))]

    dist[start] = 0
    colour[start] = "black"

    while ("white" in colour):
        min_dist = float(math.inf)
        u = -1
        for i in range(len(colour)):
            if colour[i] == "white" and float(dist[i]) < float(min_dist):
                u = i
                min_dist = dist[i]
        colour[u] = "black"
        for x in range(len(dist)):
            if colour[x] == "white":
                dist[x] = min(float(dist[x]), float(dist[u]) + float(distance_list[u][x]))

    return dist[-1]

main()