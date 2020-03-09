import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FsaImpl implements Fsa, FsaSim {
    
    private Set<State> stateSet;
    static private Set<State> curStateSet;
    static private Set<Transition> transitionSet;
    private Set<FsaListener> flSet;
    private Map<State, Set<State>> eClosedMap;
    
    private FsaListener fsaListener = null;
    
    FsaImpl(){
        this.stateSet = new HashSet<>();
        this.curStateSet = new HashSet<>();
        this.transitionSet = new HashSet<>();
        this.flSet = new HashSet<>();
        this.eClosedMap = new HashMap<>();
    };

    @Override
    public State newState(String name, int x, int y) throws IllegalArgumentException {
        if (!validName(name)) {
            throw new IllegalArgumentException("The name is not valid");
        }
        if (this.findState(name) != null) {
                throw new IllegalArgumentException("The name is the same as that of an existing state");
        }
        State newState = new MyState(name, x, y);
        stateSet.add(newState);
        Set<State> newStateEClose = new HashSet<>();
        newStateEClose.add(newState);
        this.eClosedMap.put(newState, newStateEClose);
        for (FsaListener fl: this.flSet) {
            fl.statesChanged();
        }
        return newState;
    }
    
    private boolean validName(String name) {
        
         if(name == null){             
             return false;         
         }
         
         for(int i=0;i<name.length();i++){             
             char ch = name.charAt(i);             
             if(i==0 && !Character.isLetter(ch)){                 
                 return false;             
             }             
             if(!(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_')){                 
                 return false;             
             }         
         }         
         return true;
    }

    @Override
    public void removeState(State s) {
        this.stateSet.remove(s);
        for (FsaListener fl: this.flSet) {
            fl.statesChanged();
        }
    }

    @Override
    public State findState(String stateName) {
        for (State state: this.stateSet) {
            if (state.getName().equals(stateName)) {
                return state;
            }
        }
        return null;
    }

    @Override
    public Set<State> getStates() {
        return this.stateSet;
    }
    
    public Set<Transition> getTransitions() {
        return this.transitionSet;
    }

    @Override
    public Transition newTransition(State fromState, State toState, String eventName) throws IllegalArgumentException {
        
        // if fromState exist
        if (fromState == null) {
            throw new IllegalArgumentException("The fromState does not exist");
        }
        
        // if toState exist
        if (toState == null) {
            throw new IllegalArgumentException("The toState does not exist");
        }
        
        if (!validEventName(eventName)) {
            throw new IllegalArgumentException("The eventName is invalid");
        }
            
        if (eventName == null) {
            eventName = "?";
        }
        
        if (this.findTransition(fromState, toState) != null ) {
                throw new IllegalArgumentException("An identical transition already exists");
        }
        
        Transition newTransition = new MyTransition(fromState, toState, eventName);
        this.transitionSet.add(newTransition);
        
        // key
        if (eventName.equals("?")) {
            Set<State> temp = new HashSet<>();
            for (State ms: this.eClosedMap.keySet()) {
                for (State ss : this.eClosedMap.get(ms)) {
                    if (ss.getName().equals(fromState.getName())) {
                        temp.add(toState);
                    }
                }
            }
            // ?????????????????
            this.eClosedMap.put(fromState, temp);
        }
        for (FsaListener fl: this.flSet) {
            fl.transitionsChanged();
        }
        return newTransition;
    }
    
    private boolean validEventName(String name) {
          if(name == null){             
              return true;         
          }         
          if(name.equals("?")){             
              return true;         
          }
          for(int i=0;i<name.length();i++){  
              char ch = name.charAt(i);
              if(!Character.isLetter(ch)){                 
                  return false;
              }         
          }
          return true; 
    } 

    @Override
    public void removeTransition(Transition t) {
        this.transitionSet.remove(t);
        for (FsaListener fl: this.flSet) {
            fl.transitionsChanged();
        }
    }

    @Override
    public Set<Transition> findTransition(State fromState, State toState) {
        
        if (this.findState(fromState.getName()) == null) {
            throw new IllegalArgumentException("The fromState does not exist");
        }
        
        if (this.findState(toState.getName()) == null) {
            throw new IllegalArgumentException("The toState does not exist");
        }
        
        Set<Transition> resSet = new HashSet<>();
        for (Transition tt: this.transitionSet) {
            if (tt.fromState().getName().equals(fromState.getName()) && 
                    tt.toState().getName().equals(toState.getName())) {
                resSet.add(tt);
            }
        }
        
        if (resSet.isEmpty()) {
            return null;
        }
        
        return resSet;
    }

    @Override
    public Set<State> getInitialStates() {
        Set<State> resSet = new HashSet<>();
        for (State ts: this.stateSet) {
            if (ts.isInitial()) {
                resSet.add(ts);
            }
        }
        return resSet;
    }

    @Override
    public Set<State> getFinalStates() {
        Set<State> resSet = new HashSet<>();
        for (State ts: this.stateSet) {
            if (ts.isFinal()) {
                resSet.add(ts);
            }
        }
        return resSet;
    }

    @Override
    public Set<State> getCurrentStates() {
        return this.curStateSet;
    }

    public String toString() {
        String res = "";
        for (State state: stateSet) {
            res += "STATE " + state.toString() + "\n";
        }
        for (Transition transition: transitionSet) {
            res += "TRANSITION " + transition.toString() + "\n";
        }
        for (State state: this.getInitialStates()) {
            res += "INITIAL " + state.getName() + "\n";
        }
        for (State state: this.getFinalStates()) {
            res += "FINAL " + state.getName() + "\n";
        }
        return res;
    }
    
    @Override
    public void addListener(FsaListener fl) {
        this.flSet.add(fl);
    }

    @Override
    public void removeListener(FsaListener fl) {
        this.flSet.remove(fl);
    }

    @Override
    public void reset() {
        this.curStateSet.clear();
        for (State s: this.stateSet) {
            this.curStateSet.add(s);
        }
        for (FsaListener fl: this.flSet) {
            fl.otherChanged();
        }
    }

    @Override
    public void step(String event) {
        Set<State> temp = new HashSet<>();
        for(State ks: this.eClosedMap.keySet()) {
            for (State vs: this.eClosedMap.get(ks)) {
                temp.add(vs);
            }
        }
        this.curStateSet.addAll(temp);
        temp.clear();
        for (Transition ts: this.transitionSet) {
            if (ts.eventName().equals(event)) {
                State fs = ts.fromState();
                for (State cs: this.curStateSet) {
                    if (cs.getName().equals(fs.getName())) {
                        temp.add(ts.toState());
                    }
                }
            }
        }
        this.curStateSet.clear();
        this.curStateSet.addAll(temp);
        for (FsaListener fl: this.flSet) {
            fl.otherChanged();
        }
    }

    @Override
    public boolean isRecognised() {
        for (State s: this.stateSet) {
            if (s.isFinal()) {
                return true;
            }
        }
        return false;
    }

    public static class MyState implements State {
        
        private Set<StateListener> slSet;
        private String name;
        private int x;
        private int y;
        private boolean ifInitial;
        private boolean ifFinal;
        
        public MyState(String name, int x, int y) {
            
            this.slSet = new HashSet<>();
            this.name = name;
            this.x = x;
            this.y = y;
            this.ifInitial = false;
            this.ifFinal = false;
        }

        @Override
        public void addListener(StateListener sl) {
            this.slSet.add(sl);
        }

        @Override
        public void removeListener(StateListener sl) {
            this.slSet.remove(sl);
        }
        
        @Override
        public Set<Transition> transitionsFrom() {
            Set<Transition> transitionsFrom = new HashSet<>();
            for(Transition ts: transitionSet) {
                if (ts.fromState().getName().equals(this.name)) {
                    transitionsFrom.add(ts);
                }
            }
            return transitionsFrom;
        }
        
        @Override
        public Set<Transition> transitionsTo() {
            Set<Transition> transitionsFrom = new HashSet<>();
            for(Transition ts: transitionSet) {
                if (ts.toState().getName().equals(this.name)) {
                    transitionsFrom.add(ts);
                }
            }
            return transitionsFrom;
        }
        
        @Override
        public void moveBy(int dx, int dy) {
            this.x += dx;
            this.y += dy;
            for (StateListener sl: this.slSet) {
                sl.StateHasChanged();
            }
        }
        
        public String toString() {
            String res = this.name + "(" + this.x + "," + this.y + ")";
            if (ifInitial) {
                res += "1";
            } else {
                res += "0";
            }
            if (ifFinal) { 
                res += "1";
            } else {
                res += "0";
            }
            return res;
        }
        
        
        @Override
        public String getName() {
            return this.name;
        }
        
        @Override
        public int getXpos() {
            return this.x;
        }
        
        @Override
        public int getYpos() {
            return this.y;
        }
        
        @Override
        public void setInitial(boolean b) {
            this.ifInitial = b;
            for (StateListener sl: this.slSet) {
                sl.StateHasChanged();
            }
        }
        
        @Override
        public boolean isInitial() {
            return this.ifInitial;
        }
        
        @Override
        public void setFinal(boolean b) {
            this.ifFinal = b;
            for (StateListener sl: this.slSet) {
                sl.StateHasChanged();
            }
        }
        
        @Override
        public boolean isFinal() {
            return this.ifFinal;
        }
        
        @Override
        public boolean isCurrent() {
            for (State s: curStateSet) {
                if (s.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public static class MyTransition implements Transition {
        
        private State fromState;
        private State toState;
        private String eventName;
        private Set<TransitionListener> tlSet;
        
        MyTransition(State fromState, State toState, String eventName) {
            this.fromState = fromState;
            this.toState = toState;
            this.eventName = eventName;
            this.tlSet = new HashSet<>();
        }

        @Override
        public void addListener(TransitionListener tl) {
            this.tlSet.add(tl);
        }

        @Override
        public void removeListener(TransitionListener tl) {
            this.tlSet.remove(tl);
        }

        @Override
        public State fromState() {
            return fromState;
        }

        @Override
        public State toState() {
            return toState;
        }

        @Override
        public String eventName() {
            return eventName;
        }
        
        public String toString() {
            return this.fromState.getName() + "(" + this.eventName + ")" + 
                    this.toState.getName();
        }
        
        public boolean ifEquals(Transition transition) {
            return (transition.toState().getName().equals(this.toState.getName()) && 
                    transition.fromState().getName().equals(this.fromState.getName()) &&
                    transition.eventName().equals(this.eventName));
        }
    }
    
    public static void main(String[] args) {
        FsaImpl fi = new FsaImpl();
        fi.newState("S1", 110, 22);
        fi.newState("S2", 20, 22);
        fi.newTransition(new MyState("S1", 10, 10), new MyState("S2", 20, 10), "ebv");
        System.out.println(fi.toString());
    }
}