def multiple_preference(votes):
    # dict to save votes
    vote = dict()
    for v in votes:
        if v[0] in vote.keys():
            vote[str(v[0])].append(v)
        else:
            vote[str(v[0])] = [v]
    
    # candidate list
    candidate = [i for i in vote.keys()]
    
    # process until only left two people
    while len(vote) >= 2:
        # sorted candidates by its votes
        vote = dict(sorted(vote.items(),key=lambda x:len(x[1])))
        keys = [i for i in vote.keys()]

        # if only left two candidate
        if len(vote) == 2:
            # if have same votes return 'tie', else return the bigger one
            if len(vote[keys[0]]) == len(vote[keys[1]]):
                return 'tie'
            else:
                return keys[1]

        # get candidate who fail
        remove_can = keys[0]
        remove_votes = vote[keys[0]]
        candidate.remove(remove_can)

        # remove the least candidate and add its vote to next
        for i in range(len(remove_votes)):
            vo = remove_votes[i]
            # update vote: remove failure candidate
            temp = []
            for v in vo:
                if v in candidate:
                    temp.append(v)
            # add to another candidate
            next_can = temp[0]
            vote[next_can].append(temp)

        # remove candidate from vote_dict
        del vote[remove_can]


v1 = [["a", "b", "c", "d", "e"], ["b", "e", "d", "c", "a"], ["c", "d", "e", "b", "a"], ["d", "b", "a", "c", "e"], ["e", "a", "c", "b", "d"]]
v2 = [["a", "b", "c", "d", "e"], ["b", "e", "d", "c", "a"], ["c", "d", "e", "b", "a"], ["d", "b", "a", "c", "e"], ["d", "a", "b", "c", "e"], ["e", "a", "c", "b", "d"]]
    
print(multiple_preference(v1)) #'b'
print(multiple_preference(v2)) #'tie'