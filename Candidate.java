public class Candidate
{
    private String state;
    private String divisionID;
    private String divisionName;
    private String partyAb;
    private String partyName;
    private String candidateID;
    private String surname; 
    private String givenName;
    private char elected;
    private char historicElected;
    
    public Candidate(String inState, String inDivisionID, String inDivisionName,
                     String inPartyAb, String inPartyName, String inCandidateID,
                     String inSurname, String inGivenName, char inElected,
                     char inHistoricElected)
    {
        setState(inState);
        setDivisionID(inDivisionID);
        setDivisionName(inDivisionName);
        setPartyAb(inPartyAb);
        setPartyName(inPartyName);
        setCandidateID(inCandidateID);
        setSurname(inSurname);
        setGivenName(inGivenName);
        setElected(inElected);
        setHistoricElected(inHistoricElected);
    }
    
    public String setState()
    {
        return state;
    }
    
    public String setPartyAb()
    {
        return partyAb;
    }
    
    public String setDivisionID()
    {
        return divisionID;
    }
    
    public String getCompare(int fieldToCompare)
    {
        String string;
        switch(fieldToCompare)
        {
            case 1:
                string = surname;
                break;
            case 2:
                string = state;
                break;
            case 3:
                string = partyAb;
                break;
            default:
                string = divisionID;
        }
        return string;
    }
    
    public String toString()
    {
        String string = state + "," + divisionID + "," + divisionName + "," + partyAb + "," + partyName + "," + candidateID + "," + surname + "," + givenName + "," + elected + "," + historicElected;
        return string;
    }
    
    public String toStringSimple()
    {
        String string = surname + " " + givenName;
    }
    
    public void setState(String inState)
    {
        if(validateString(inState))
        {
            state = inState;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid state");
        }
    }
    
    public void setDivisionID(String inDivisionID)
    {
        if(validateString(inDivisionID))
        {
            divisionID = inDivisionID;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid divisionID");
        }
    }
    
    public void setDivisionName(String inDivisionName)
    {
        if(validateString(inDivisionName))
        {
            divisionName = inDivisionName;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid divisionName");
        }
    }
    
    public void setPartyAb(String inPartyAb)
    {
        if(validateString(inPartyAb))
        {
            partyAb = inPartyAb;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid partyAb");
        }
    }
    
    public void setPartyName(String inPartyName)
    {
        if(validateString(inPartyName))
        {
            partyName = inPartyName;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid partyName");
        }
    }
    
    public void setCandidateID(String inCandidateID)
    {
        if(validateString(inCandidateID))
        {
            candidateID = inCandidateID;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid candidateID");
        }
    }
    
    
    public void setSurname(String inSurname)
    {
        if(validateString(inSurname))
        {
            surname = inSurname;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid surname");
        }
    }
    
    public void setCandidateID(String inGivenName)
    {
        if(validateString(inGivenName))
        {
            givenName = inGivenName;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid givenName");
        }
    }
    
    public void setElected(char inElected)
    {
        if((inElected == 'Y') || (inElected == 'N'))
        {
            elected = inElected;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid elected");
        }
    }
    
    public void setHistoricElected(char inHistoricElected)
    {
        if((inHistoricElected == 'Y') || (inHistoricElected == 'N'))
        {
            historicElected = inHistoricElected;
        }
        else 
        {
            throw new IllegalArgumentException("Invalid historicElected");
        }
    }
    
    private boolean validateString(String string)
    {
        boolean valid;
        valid = (!((inString.equals("")) || (inString.equals(null))));
        return valid;
    }
}