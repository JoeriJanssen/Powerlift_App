package PowerLifters.PowerLiften.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import PowerLifters.PowerLiften.domein.GegevenTraining;
import PowerLifters.PowerLiften.domein.GeregistreerdeSporter;
import PowerLifters.PowerLiften.domein.Planning;

@Service
@Transactional
public class PlanningService {
	@Autowired
	PlanningRepository ps;
	
	@Autowired
	GeregistreerdeSporterRepository gsr;
	
	@Autowired
	GegevenTrainingRepository gtr;
	
	public void opslaanSporter(long planningID, long sporterID) {
		GeregistreerdeSporter gs = gsr.findById(sporterID).get();
		Planning p = ps.findById(planningID).get();
		p.setGeregistreerdeSporter(gs);
		ps.save(p);
		
	}
	
	
	public void opslaanOefening(Planning p, long trainingID) {
		GegevenTraining gt = gtr.findById(trainingID).get();
		p.addTraining(gt);
		ps.save(p);
		
	}
	public Planning getPlanningByID(long planningID) {
		return ps.findById(planningID).get();
	}
	
	public Iterable<Planning> vindPlanning(){
		Iterable<Planning> p = ps.findAll();
		return p;
	}
	
	public void verwijderPlanning(Long id) {
		System.out.println("Planning: " + id + " wordt verwijderd");
		ps.deleteById(id);
	}

	public void opslaanPlanning(Planning p) {
		ps.save(p);
		
	}

	public void testingFelix(Planning p, long trainingID) {
		GegevenTraining gt = gtr.findById(trainingID).get();
		p.getTraining().add(gt);
		ps.save(p);
	}


	public Planning vindPlanningVoorSporter(long sporterID) {
		GeregistreerdeSporter sporter = gsr.findById(sporterID).get();
		System.out.println(sporter.getNaam());
		Planning planning = ps.findByGeregistreerdeSporter(sporter);
		System.out.println(planning);
		return planning;
	}
}
