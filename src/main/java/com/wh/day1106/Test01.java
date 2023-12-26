package com.wh.day1106;

import io.netty.util.internal.StringUtil;

import java.util.*;

/**
 * Author: wanghao
 * Date: 2023/11/6 15:07
 * Description:
 */
public class Test01 {

    public static void main(String[] args) {

        List<Long> ids = new ArrayList<>();
        ids.add(1000026878785L);
        ids.add(1000026878866L);
        ids.add(1000026878869L);
        ids.add(1000026914893L);
        ids.add(1000026615202L);
        ids.add(1000027305133L);
        ids.add(1000028312849L);
        ids.add(1000028313345L);
        ids.add(1000028310389L);
        ids.add(1000026878658L);

        Map<String, Set<Long>> stringSetMap = groupAudioIdsByTableName(ids);
        for ( String partitionName : stringSetMap.keySet() ) {
            System.out.println( "partitionName = " + partitionName );
            Set<Long> audioIds = stringSetMap.get( partitionName );
            for ( Long audioId : audioIds ) {
                System.out.println( "audioId = " + audioId );
            }
        }

        //td_audio_opus_9
        //audioId = 1000026878869
        //audioId = 1000028310389
        //audioId = 1000028312849

        //"SELECT `id`,audio_id,bitrate,file_md5,`type`,file_size,time_length FROM td_audio_opus_9 WHERE audio_id IN (1000026878869,1000028310389,1000028312849)"

        //td_audio_opus_5
        //audioId = 1000026878785
        //audioId = 1000028313345


    }

    private static String getPartitionNameByAudioId( Long audioId ) {
        String TABLE_NAME_PREFIX = "td_audio_opus_";
        long tableIndex = audioId % 10;
        return TABLE_NAME_PREFIX + tableIndex;
    }

    private static Map<String, Set<Long>> groupAudioIdsByTableName(List<Long> audioIds) {
        Map<String, Set<Long>> partitionNameAndAudioIds = new HashMap<>();
        for ( Long audioId : audioIds ) {
            if ( audioId == null ) {
                continue;
            }
            String partitionName = getPartitionNameByAudioId( audioId );
            Set<Long> audioIdsByPartitionName = partitionNameAndAudioIds.get( partitionName );
            if ( audioIdsByPartitionName == null ) {
                audioIdsByPartitionName = new HashSet<>();
            }
            audioIdsByPartitionName.add( audioId );
            partitionNameAndAudioIds.put( partitionName, audioIdsByPartitionName );
        }
        return partitionNameAndAudioIds;
    }


}
