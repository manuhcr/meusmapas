import {useState} from 'react';
import {Button, Card, Modal, Form, Row, Col} from 'react-bootstrap'


function Map() {
    const [maps, setMaps] = useState([])
    const [countId, setCountId] = useState(1)
    const [modalShow, setModalShow] = useState(false);
    const [abrirModal, setAbrirModal] = useState(false);
    const [nomeMapa, setNomeMapa] = useState(' ');
    const [mapaAtivo, setMapaAtivo] = useState(null)


    function createMap() {
        let newList = []
        let newMap = {
            id: countId,
            nomeMapa: nomeMapa,
        };
        newList = [...maps, newMap];
        setMaps(newList);
        setCountId(countId + 1)
        setNomeMapa('');

    }

    function activeMap(achaPorId) {

    }

    function excludeMap(removePorId) {
        let novaList = maps.filter(mapa => mapa.id !== removePorId)
        setMaps(novaList)

    }

    function excludeAll() {
        let listaSemNada = [];
        setMaps(listaSemNada)
    }

    return (<>

        <Modal
            show={modalShow}
            onHide={() => setModalShow(false)}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >

            <Modal.Body>
                <h4>Tem certeza que quer excluir todos os mapas?</h4>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={() => {
                    excludeAll();
                    setModalShow(false);
                }}>Sim</Button>
                <Button onClick={() => setModalShow(false)}>Não</Button>
            </Modal.Footer>
        </Modal>
        <Modal show={abrirModal} onHide={() => setAbrirModal(false)} centered>
            <Modal.Header>
                <Modal.Title>Criar novo mapa</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                <Form>
                    <Form.Group as={Row} className="mb-3" controlId="formHorizontalPassword">
                        <Form.Label column sm={10}>
                            Nome do mapa:
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="text"
                                          placeholder="Nome"
                                          value={nomeMapa}
                                          onChange={(e) => setNomeMapa(e.target.value)}/>
                        </Col>
                    </Form.Group>
                </Form>
            </Modal.Body>

            <Modal.Footer>
                <Button className='botaoCancel' variant="secondary" onClick={() => setAbrirModal(false)}>
                    Cancelar
                </Button>

                <Button className='botaoCreate' variant="primary" onClick={createMap}>
                    Criar
                </Button>
            </Modal.Footer>
        </Modal>
        <Button onClick={() => setAbrirModal(true)} className='botaoCria'>+ Criar novo mapa</Button>

        {maps.map(mapa => (<Card className="mapas" key={mapa.id}>
            <ul>
                <div>
                    <li>{mapa.nomeMapa}< /li>
                    <p><span>0</span> mapas cadastrados</p>
                </div>

                <div className='botoes'><Button className='botao'>Acessar</Button>
                    <Button className='botaoExclude' onClick={() => excludeMap(mapa.id)}>Excluir</Button></div>

            </ul>
        </Card>))}
        <Button className='botaoExcAll' variant="primary" onClick={() =>
            setModalShow(true)}>Excluir tudo</Button>

    </>)
}

export default Map