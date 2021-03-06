package org.nd4j.linalg.api.ops.impl.transforms;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.imports.descriptors.properties.AttributeAdapter;
import org.nd4j.imports.descriptors.properties.PropertyMapping;
import org.nd4j.imports.descriptors.properties.adapters.StringEqualsAdapter;
import org.nd4j.imports.descriptors.properties.adapters.StringNotEqualsAdapter;
import org.nd4j.imports.graphmapper.tf.TFGraphMapper;
import org.nd4j.linalg.api.ops.DynamicCustomOp;
import org.tensorflow.framework.AttrValue;
import org.tensorflow.framework.GraphDef;
import org.tensorflow.framework.NodeDef;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class MirrorPad extends DynamicCustomOp {
    protected boolean isSymmetric = false;

    public MirrorPad() {
        //
    }

    @Override
    public void initFromTensorFlow(NodeDef nodeDef, SameDiff initWith, Map<String, AttrValue> attributesForNode, GraphDef graph) {
        TFGraphMapper.getInstance().initFunctionFromProperties(nodeDef.getOp(), this, attributesForNode, nodeDef, graph);
        iArguments.add(isSymmetric ? 1L : 0L);
    }

    @Override
    public String opName() {
        return "mirror_pad";
    }

    @Override
    public String tensorflowName() {
        return "MirrorPad";
    }

    @Override
    public Map<String, Map<String, AttributeAdapter>> attributeAdaptersForFunction() {
        Map<String, Map<String, AttributeAdapter>> ret = new HashMap<>();
        Map<String, AttributeAdapter> tfMappings = new LinkedHashMap<>();

        // :)
        tfMappings.put("isSymmetric", new StringNotEqualsAdapter("REFLECT"));

        ret.put(tensorflowName(), tfMappings);

        return ret;
    }

    @Override
    public Map<String, Map<String, PropertyMapping>> mappingsForFunction() {
        Map<String, Map<String, PropertyMapping>> ret = new HashMap<>();
        Map<String, PropertyMapping> map = new HashMap<>();

        val symmetric = PropertyMapping.builder()
                .tfAttrName("mode")
                .propertyNames(new String[]{"isSymmetric"})
                .build();

        map.put("isSymmetric", symmetric);

        return ret;
    }
}
