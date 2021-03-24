package microservices.book.multiplication.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

@Component
public class MapUtil {

	public void incrementMap(Map<NotesAccSep, Integer> individualNoteCount, NotesAccSep notesAcc) {

			if (individualNoteCount.containsKey(notesAcc)) {
				individualNoteCount.put(notesAcc, individualNoteCount.get(notesAcc) + 1);
			} else {


				individualNoteCount.put(notesAcc, 1);
			}


	}
	public void incrementMap(Map<String, Integer> map, String key, int points) {
		
		if (map.containsKey(key) && points > map.get(key)) {
			map.put(key, points);
		}else if(map.containsKey(key)) {
				
			}else {
				map.put(key, points);
			}
		
		
	}
	public void decrementMap(Map<String, Integer> map, String key) {
		
		if (map.containsKey(key)) {
			map.put(key, map.get(key) - 1);
		}else {
			map.put(key, -1);
		}
		
		
	}

	public void incrementTimeMap(Map<NotesAccSep, Double> map, NotesAccSep key, double reactionTime) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + reactionTime);
		} else {
			map.put(key, reactionTime);
		}
	}

	public <K, V extends Comparable<V>> V maxMap(Map<K, V> map) {
		Optional<Entry<K, V>> maxEntry = map.entrySet().stream().max(Comparator.comparing(Entry::getValue));
		return maxEntry.get().getValue();
	}

	public Comparator<Entry<NotesAccSep, Integer>> valueComparator = new Comparator<Entry<NotesAccSep, Integer>>() {
		@Override
		public int compare(Entry<NotesAccSep, Integer> e1, Entry<NotesAccSep, Integer> e2) {
			NotesAccSep v1 = e1.getKey();
			NotesAccSep v2 = e2.getKey();
			return v1.compareTo(v2);
		}
	};
	public Comparator<Entry<NotesAccSep, Integer>> valueComparatorDesc = new Comparator<Entry<NotesAccSep, Integer>>() {
		@Override
		public int compare(Entry<NotesAccSep, Integer> e1, Entry<NotesAccSep, Integer> e2) {
			NotesAccSep v1 = e1.getKey();
			NotesAccSep v2 = e2.getKey();
			return v2.compareTo(v1);
		}
	};

	public Comparator<Entry<String, Integer>> scoreComparator = new Comparator<Entry<String, Integer>>() {
		@Override
		public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
			Integer v1 = e1.getValue();
			Integer v2 = e2.getValue();
			return v2.compareTo(v1);
		}
	};

	public Comparator<Entry<NotesAccSep, Double>> valueComparatorTime = new Comparator<Entry<NotesAccSep, Double>>() {
		@Override
		public int compare(Entry<NotesAccSep, Double> e1, Entry<NotesAccSep, Double> e2) {
			NotesAccSep v1 = e1.getKey();
			NotesAccSep v2 = e2.getKey();
			return v1.compareTo(v2);
		}
	};
}
