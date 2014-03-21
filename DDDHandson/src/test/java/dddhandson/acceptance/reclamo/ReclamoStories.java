package dddhandson.acceptance.reclamo;

import dddhandson.acceptance.support.ItalianStories;

public class ReclamoStories extends ItalianStories {

    @Override
    public String nomeModulo() {
        return "reclamo";
    }

    @Override
    public Object step() {
        return new ReclamoStorySteps();
    }
}
