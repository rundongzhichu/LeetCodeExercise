package MianShiTi.ZiJie.Exercise1;

import java.util.*;

public class MaoMiFeature {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int useCase = Integer.valueOf(in.next());

        for (int i = 0; i < useCase; i++) {
            int frameCount = Integer.valueOf(in.next());
            Map<String, Integer> record = null;
            int maxFeatureLen = 1;
            for (int j = 0; j < frameCount; j++) {
                int featureCount = Integer.valueOf(in.next());
                Map<String, Integer> temp = new HashMap<>();
                for (int k = 0; k < featureCount; k++) {
                    String feature = in.next()+in.next();
                    temp.put(feature, 1);
                }
                if(record == null || record.isEmpty()){
                    record = temp;
                } else {
                    Set<String> notOccurrance = new HashSet<>();
                    for(String key: record.keySet()){
                        if(!temp.containsKey(key)){
                            notOccurrance.add(key);
                        }
                    }

                    for(String key: notOccurrance){
                        record.remove(key);
                    }

                    for (String key: temp.keySet()){
                        record.put(key, record.getOrDefault(key, 0)+1);
                        maxFeatureLen = Math.max(maxFeatureLen, record.get(key));
                    }
                }
            }
            System.out.println(maxFeatureLen);
        }
        
    }
}
