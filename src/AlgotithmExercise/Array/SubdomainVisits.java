package AlgotithmExercise.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisits {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> records = new HashMap<>();

        for(String cpdomain: cpdomains){
            String[] domain = cpdomain.split(" ");
            int visitCount = Integer.valueOf(domain[0].trim());

            String subDomain = domain[1];
            int index = subDomain.indexOf('.');
            while (index != -1){
                records.put(subDomain, records.getOrDefault(subDomain,0)+visitCount);
                subDomain = subDomain.substring(index+1, subDomain.length());
                System.out.println("temp = " + subDomain);
                index = subDomain.indexOf('.');
            }
            records.put(subDomain, records.getOrDefault(subDomain,0)+visitCount);
        }

        List<String> results = new ArrayList<>();
        for(String domain: records.keySet()){
            results.add(String.valueOf(records.get(domain))+ " " + domain);
        }
        return results;
    }


    public static void main(String[] args) {
        String[] domain = "discuss.leetcode.com".split("\\.");
        System.out.println("domain = " + domain[0]);
    }

}
