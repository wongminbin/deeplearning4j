package org.nd4j.linalg.api.ops.impl.controlflow.compat;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ops.Op;
import org.tensorflow.framework.AttrValue;
import org.tensorflow.framework.GraphDef;
import org.tensorflow.framework.NodeDef;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NextIteration extends BaseCompatOp {
    @Override
    public String opName() {
        return "next_iteration";
    }

    @Override
    public List<long[]> calculateOutputShape() {
        if(arg().getArr() != null) {
            return Collections.singletonList(arg().getShape());
        }
        else
            return Collections.emptyList();
    }

    @Override
    public SDVariable[] outputVariables() {
        return super.outputVariables();
    }

    @Override
    public String tensorflowName() {
        return "NextIteration";
    }

    @Override
    public Op.Type opType() {
        return Op.Type.NEXT_ITERATION;
    }

    @Override
    public void initFromTensorFlow(NodeDef nodeDef, SameDiff initWith, Map<String, AttrValue> attributesForNode, GraphDef graph) {
        super.initFromTensorFlow(nodeDef, initWith, attributesForNode, graph);
    }

    @Override
    public int getNumOutputs(){
        return 1;
    }
}
