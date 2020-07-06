package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.util.BaseException;

public interface IcomManager {
	public List<Beancommodity_information> loadcom()throws BaseException;
}
