package com.comrades.core.bases;

import java.time.LocalDate;
import java.util.*;

public class CloneUtils {
	public static <T> Collection<T> clone(Collection<T> toClone) {
		if (toClone == null) {
			return Collections.emptyList();
		}
		return new ArrayList<>(toClone);
	}

	public static <T> Set<T> clone(Set<T> toClone) {
		if (toClone == null) {
			return Collections.emptySet();
		}
		return new HashSet<>(toClone);
	}

	public static <T> SortedSet<T> clone(SortedSet<T> toClone) {
		if (toClone == null) {
			return new TreeSet<>();
		}
		return new TreeSet<>(toClone);
	}

	public static Date clone(Date toClone) {
		if (toClone == null) {
			return null;
		}
		return new Date(toClone.getTime());
	}

	public static LocalDate clone(LocalDate toClone) {
		if (toClone == null) {
			return null;
		}
		return LocalDate.of(toClone.getYear(),toClone.getMonthValue(),toClone.getDayOfMonth());
	}

	public static <E> List<E> clone(List<E> toClone) {
		if (toClone == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(toClone);
	}

	public static byte[] clone(byte[] toClone) {
		if (toClone == null) {
			return toClone;
		}
		return toClone.clone();
	}

	public static <E> List<E> replaceItems(List<E> target, List<E> items) {
		if (items == null) {
			return new ArrayList<>();
		}
		else {
			if (target == null) {
				target = new ArrayList<>();
			}
			target.clear();
			target.addAll(items);
			return target;
		}
	}

	public static <E> SortedSet<E> replaceItems(SortedSet<E> target, Collection<E> items) {
		if (items == null) {
			return new TreeSet<>();
		}else {
			if (target == null) {
				target = new TreeSet<>();
			}
			target.clear();
			target.addAll(items);
			return target;
		}
	}
}