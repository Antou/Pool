package pool.action.scheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import pool.action.Action;
import pool.action.exception.ActionFinishedException;

public class SequentialSchedulerTest extends SchedulerTest {
	
	protected Action createAction() {
		return new SequentialScheduler();
	}
	
	
	@Test
	public void nextActionTest() throws ActionFinishedException {
		this.scheduler.addAction(this.action2);
		this.scheduler.doStep();
		assertEquals(this.action1,this.scheduler.nextAction());
		this.scheduler.doStep();
		assertEquals(this.action2,this.scheduler.nextAction());
	}
	
	@Test
	public void removeActionIfFinished() throws ActionFinishedException {
		assertFalse(this.scheduler.removeActionIfFinished(this.action1));
		while(!this.scheduler.isFinished()) {
			this.scheduler.doStep();
		}
		
		/*checks if the action has been removed from the scheduler as it's finished*/
		assertTrue(this.scheduler.isFinished());
	}

}

