package edu.java.controller;

import edu.java.model.Trainer;

public interface TrainerDao {
	
	/**
	 * 트레이너 가입
	 * JoinView에서 가입
	 * 
	 * @param trainer
	 * @return 생성 시 1, 비생성 시 0
	 */
	int insertTrainer(Trainer trainer);
	
	/**
	 * 트레이너 정보
	 * 
	 * @param id
	 * @return trainer
	 */
	Trainer selectTrainerInfo(String id);
	
}
